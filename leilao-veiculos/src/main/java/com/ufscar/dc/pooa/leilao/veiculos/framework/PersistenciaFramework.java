package com.ufscar.dc.pooa.leilao.veiculos.framework;

import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersistenciaFramework {
	private static final AppLogger log = AppLoggerFactory.getAppLogger(PersistenciaFramework.class);

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	public void save(Object entidade) {
		String tableName = getTableName(entidade.getClass());
		List<Field> camposAnotados = Arrays.stream(entidade.getClass().getDeclaredFields())
				.filter(f -> f.isAnnotationPresent(PersistenciaCampo.class))
				.toList();
		if (camposAnotados.isEmpty()) {
			log.error("Falha ao salvar com framework de persistência: Nenhum campo anotado com @PersistenciaCampo encontrado");
			throw new BadRequestException("Nenhum campo anotado com @PersistenciaCampo encontrado");
		}
		String nomesColunas = camposAnotados.stream()
				.map(campoAnotado -> campoAnotado.getAnnotation(PersistenciaCampo.class).nome())
				.collect(Collectors.joining(", "));
		String placeholders = camposAnotados.stream().map(campoAnotado -> "?").collect(Collectors.joining(", "));
		String sql = "INSERT INTO " + tableName + " (" + nomesColunas + ") VALUES (" + placeholders + ")";
		log.debug("SQL gerado para SAVE: {}", sql);
		try {
			Connection connection = conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0; i < camposAnotados.size(); i++) {
				Field campo = camposAnotados.get(i);
				campo.setAccessible(true);
				Object valor = campo.get(entidade);
				statement.setObject(i + 1, valor);
			}
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Falha ao salvar com framework de persistência: {}", e.getMessage(), e);
			throw new BadRequestException("Falha ao salvar com framework de persistência");
		}
	}

	public void delete(Class<?> clazz, String campoChave, Object valor) {
		String tableName = getTableName(clazz);
		String columnName = getColumnName(clazz, campoChave);
		String sql = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
		log.debug("SQL gerado para DELETE: {}", sql);
		try {
			Connection connection = conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, valor);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Falha ao deletar com framework de persistência: {}", e.getMessage(), e);
			throw new BadRequestException("Falha ao deletar com framework de persistência");
		}
	}

	public List<Object> findAll(Class<?> clazz) {
		String tableName = getTableName(clazz);
		List<Object> resultados = new ArrayList<>();
		String sql = "SELECT * FROM " + tableName;
		log.debug("SQL gerado para findAll: {}", sql);
		try {
			Connection connection = conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Object instancia = clazz.getDeclaredConstructor().newInstance();
				for (Field campo : clazz.getDeclaredFields()) {
					if (campo.isAnnotationPresent(PersistenciaCampo.class)) {
						PersistenciaCampo anotacaoCampo = campo.getAnnotation(PersistenciaCampo.class);
						campo.setAccessible(true);
						Object valorColuna = rs.getObject(anotacaoCampo.nome());
						if (campo.getType().equals(LocalDateTime.class) && valorColuna instanceof Timestamp) {
							campo.set(instancia, ((Timestamp) valorColuna).toLocalDateTime());
						} else {
							campo.set(instancia, valorColuna);
						}
					}
				}
				resultados.add(instancia);
			}
			statement.close();
			connection.close();
			return resultados;
		} catch (Exception e) {
			log.error("Falha ao buscar entidades com framework de persistência: {}", e.getMessage(), e);
			throw new BadRequestException("Falha ao buscar entidades com framework de persistência");
		}
	}

	public Optional<Object> findOneBy(Class<?> clazz, String campoChave, Object valor) {
		String tableName = getTableName(clazz);
		String columnName = getColumnName(clazz, campoChave);
		String sql = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ? LIMIT 1";
		log.debug("SQL gerado para findOneBy: {}", sql);
		try {
			Connection connection = conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, valor);
			ResultSet rs = statement.executeQuery();
			Optional<Object> resultado = Optional.empty();
			if (rs.next()) {
				Object instancia = clazz.getDeclaredConstructor().newInstance();
				for (Field campo : clazz.getDeclaredFields()) {
					if (campo.isAnnotationPresent(PersistenciaCampo.class)) {
						PersistenciaCampo anotacaoCampo = campo.getAnnotation(PersistenciaCampo.class);
						campo.setAccessible(true);
						Object valorColuna = rs.getObject(anotacaoCampo.nome());
						if (campo.getType().equals(LocalDateTime.class) && valorColuna instanceof Timestamp) {
							campo.set(instancia, ((Timestamp) valorColuna).toLocalDateTime());
						} else {
							campo.set(instancia, valorColuna);
						}
					}
				}
				resultado = Optional.of(instancia);
			}
			statement.close();
			connection.close();
			return resultado;
		} catch (Exception e) {
			log.error("Falha ao encontrar entidade com framework de persistência: {}", e.getMessage(), e);
			throw new BadRequestException("Falha ao encontrar entidade com framework de persistência");
		}
	}

	public boolean doesExist(Class<?> clazz, String campoChave, Object valor) {
		String tableName = getTableName(clazz);
		String columnName = getColumnName(clazz, campoChave);
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?";
		log.debug("SQL gerado para doesExist: {}", sql);
		try {
			Connection connection = conectar();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, valor);
			ResultSet rs = statement.executeQuery();
			boolean resultado = false;
			if (rs.next()) {
				int count = rs.getInt(1);
				resultado = count > 0;
			}
			statement.close();
			connection.close();
			return resultado;
		} catch (Exception e) {
			log.error("Falha ao verificar se entidade existe com framework de persistência: {}", e.getMessage(), e);
			throw new BadRequestException("Falha ao verificar se entidade existe com framework de persistência");
		}
	}

	private String getTableName(Class<?> clazz) {
		if (!clazz.isAnnotationPresent(PersistenciaTabela.class)) {
			log.error("A classe {} não está anotada com @PersistenciaTabela", clazz.getName());
			throw new IllegalArgumentException("A classe " + clazz.getName()
				+ " não está anotada com @PersistenciaTabela");
		}
		PersistenciaTabela anotacao = clazz.getAnnotation(PersistenciaTabela.class);
		return anotacao.schema().isEmpty() ? anotacao.nome() : anotacao.schema() + "." + anotacao.nome();
	}

	private String getColumnName(Class<?> clazz, String campo) {
		if (!clazz.isAnnotationPresent(PersistenciaTabela.class)) {
			log.error("A classe {} não está anotada com @PersistenciaTabela", clazz.getName());
			throw new IllegalArgumentException("A classe " + clazz.getName()
				+ " não está anotada com @PersistenciaTabela");
		}
		try {
			Field field = clazz.getDeclaredField(campo);
			if (!field.isAnnotationPresent(PersistenciaCampo.class)) {
				log.error("O campo '{}' não está anotado com @PersistenciaCampo na classe {}", 
						campo, clazz.getSimpleName());
				throw new IllegalArgumentException("O campo '" + campo +
						"' não está anotado com @PersistenciaCampo");
			}
			PersistenciaCampo anotacaoCampo = field.getAnnotation(PersistenciaCampo.class);
			return anotacaoCampo.nome();
		} catch (Exception e) {
			log.error("Falha ao obter o nome da coluna para o campo '{}' na classe {}: {}", campo, clazz.getSimpleName(), e.getMessage());
			throw new IllegalArgumentException("Campo '" + campo + "' não encontrado na classe " + clazz.getSimpleName());
		}
	}

	private Connection conectar() throws SQLException {
		log.debug("Abrindo nova conexão com o banco de dados.");
		return DriverManager.getConnection(url, username, password);
	}
}
