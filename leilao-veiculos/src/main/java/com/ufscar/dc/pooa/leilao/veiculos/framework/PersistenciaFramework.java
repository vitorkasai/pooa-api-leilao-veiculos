package com.ufscar.dc.pooa.leilao.veiculos.framework;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;

public class PersistenciaFramework {
	public void save(Object entidade) {
		String tableName = getTableName(entidade.getClass());

		List<Field> camposAnotados = Arrays.stream(entidade.getClass().getDeclaredFields())
				.filter(f -> f.isAnnotationPresent(PersistenciaCampo.class))
				.toList();

		if (camposAnotados.isEmpty()) {
			throw new BadRequestException("Nenhum campo anotado com @PersistenciaCampo encontrado");
		}

		String nomesColunas = camposAnotados.stream()
				.map(campoAnotado -> campoAnotado.getAnnotation(PersistenciaCampo.class).nome())
				.collect(Collectors.joining(", "));
		String placeholders = camposAnotados.stream().map(campoAnotado -> "?").collect(Collectors.joining(", "));
		String sql = "INSERT INTO " + tableName + " (" + nomesColunas + ") VALUES (" + placeholders + ")";
		
		try {
			Connection connection = conectar();
			PreparedStatement stmt = connection.prepareStatement(sql);
			for (int i = 0; i < camposAnotados.size(); i++) {
				Field campo = camposAnotados.get(i);
				campo.setAccessible(true);
				Object valor = campo.get(entidade);
				stmt.setObject(i + 1, valor);
			}
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			throw new BadRequestException("Falha ao salvar com framework de persistência");
		}
	}

	public List<Object> findAll(Class<?> clazz) throws Exception {
		String tableName = getTableName(clazz);
		List<Object> resultados = new ArrayList<>();

		String sql = "SELECT * FROM " + tableName;

		try (Connection conn = conectar();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Object instancia = clazz.getDeclaredConstructor().newInstance();

				for (Field campo : clazz.getDeclaredFields()) {
					if (campo.isAnnotationPresent(PersistenciaCampo.class)) {
						PersistenciaCampo anotacaoCampo = campo.getAnnotation(PersistenciaCampo.class);
						campo.setAccessible(true);
						campo.set(instancia, rs.getObject(anotacaoCampo.nome()));
					}
				}

				resultados.add(instancia);
			}
		}

		return resultados;
	}

	public boolean doesExist(Class<?> clazz, String campoChave, Object valor) throws Exception {
		String tableName = getTableName(clazz);
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + campoChave + " = ?";

		try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setObject(1, valor);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		}

		return false;
	}

	private String getTableName(Class<?> clazz) {
		if (!clazz.isAnnotationPresent(PersistenciaTabela.class)) {
			throw new IllegalArgumentException("A classe " + clazz.getName() + " não está anotada com @Tabela");
		}

		PersistenciaTabela anotacao = clazz.getAnnotation(PersistenciaTabela.class);
		return anotacao.schema().isEmpty() ? anotacao.nome() : anotacao.schema() + "." + anotacao.nome();
	}

	private Connection conectar() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root"); // TODO mudar para puxar das properties
	}
}
