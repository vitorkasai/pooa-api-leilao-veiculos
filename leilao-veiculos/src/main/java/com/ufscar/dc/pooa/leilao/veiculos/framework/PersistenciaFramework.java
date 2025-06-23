package com.ufscar.dc.pooa.leilao.veiculos.framework;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaFramework {
	public void save(Object entidade) throws SQLException, IllegalAccessException {
		Class<?> clazz = entidade.getClass();
		String nomeTabela = getTableName(clazz);

		StringBuilder nomesColunas = new StringBuilder();
		StringBuilder placeholders = new StringBuilder();

		Field[] campos = clazz.getDeclaredFields();
		int totalCampos = 0;

		for (Field campo : campos) {
			if (campo.isAnnotationPresent(Campo.class)) {
				Campo anotacaoCampo = campo.getAnnotation(Campo.class);
				nomesColunas.append(anotacaoCampo.nome()).append(",");
				placeholders.append("?").append(",");
				totalCampos++;
			}
		}

		if (totalCampos == 0) {
			throw new IllegalArgumentException("Nenhum campo anotado com @Campo encontrado");
		}

		nomesColunas.setLength(nomesColunas.length() - 1);
		placeholders.setLength(placeholders.length() - 1);

		String sql = "INSERT INTO " + nomeTabela + " (" + nomesColunas + ") VALUES (" + placeholders + ")";

		try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			int index = 1;
			for (Field campo : campos) {
				if (campo.isAnnotationPresent(Campo.class)) {
					campo.setAccessible(true);
					Object valor = campo.get(entidade);
					stmt.setObject(index++, valor);
				}
			}

			stmt.executeUpdate();
		}
	}

	public List<Object> findAll(Class<?> clazz) throws Exception {
		String nomeTabela = getTableName(clazz);
		List<Object> resultados = new ArrayList<>();

		String sql = "SELECT * FROM " + nomeTabela;

		try (Connection conn = conectar();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Object instancia = clazz.getDeclaredConstructor().newInstance();

				for (Field campo : clazz.getDeclaredFields()) {
					if (campo.isAnnotationPresent(Campo.class)) {
						Campo anotacaoCampo = campo.getAnnotation(Campo.class);
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
		String nomeTabela = getTableName(clazz);
		String sql = "SELECT COUNT(*) FROM " + nomeTabela + " WHERE " + campoChave + " = ?";

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
		if (!clazz.isAnnotationPresent(Tabela.class)) {
			throw new IllegalArgumentException("A classe " + clazz.getName() + " não está anotada com @Tabela");
		}

		Tabela anotacao = clazz.getAnnotation(Tabela.class);
		return anotacao.schema().isEmpty() ? anotacao.nome() : anotacao.schema() + "." + anotacao.nome();
	}

	private Connection conectar() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root"); // TODO mudar para puxar das properties
	}
}
