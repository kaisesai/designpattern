package com.liukai.pattern.create.factorymethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {

	private static QueryRunner queryRunner;

	public static void main(String[] args) throws SQLException {
		// 创建一个工厂类
		queryRunner = new OracleQueryRunner();
		ResultSet resultSet = queryRunner.run();
		System.out.println(resultSet);
	}

}

/**
 * 抽象工厂类
 * <p>
 * 抽象查询器
 * </p>
 * Created by Administrator on 2016/7/16 0016.
 */
abstract class QueryRunner {

	/**
	 * 执行查询
	 *
	 * @return 抽象结果集
	 */
	public ResultSet run() throws SQLException {
		Connection connection = createConnection();
		String sql = createSql();
		return runSql(connection, sql);
	}

	/**
	 * 创建连接
	 *
	 * @return 抽象连接器
	 */
	protected abstract Connection createConnection();

	/**
	 * 创建SQL语句
	 *
	 * @return
	 */
	protected abstract String createSql();

	/**
	 * 运行SQL语句
	 *
	 * @param connection
	 * @param sql
	 * @return 抽象结果集
	 */
	protected abstract ResultSet runSql(Connection connection, String sql) throws SQLException;

}

/**
 * 具体工厂类 Sybase查询器
 */
class SybaseQueryRunner extends QueryRunner {

	protected Connection createConnection() {
		return null;
	}

	protected String createSql() {
		return "select * from customers";
	}

	protected ResultSet runSql(Connection connection, String sql) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		return ps.executeQuery();
	}
}

/**
 * 具体工厂类 Oracle查询器
 */
class OracleQueryRunner extends QueryRunner {

	protected Connection createConnection() {
		return null;
	}

	protected String createSql() {
		return "select * from customers";
	}

	protected ResultSet runSql(Connection connection, String sql) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		return ps.executeQuery();
	}
}
