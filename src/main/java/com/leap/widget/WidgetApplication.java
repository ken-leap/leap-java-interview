package com.leap.widget;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WidgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(WidgetApplication.class, args);
	}

	@RestController
	public static class Controller {

		@Value("${spring.datasource.url:}")
		private String jdbcUrl;

		@Value("${jdbc.username:}")
		private String username;

		@Value("${jdbc.password:}")
		private String password;

		@GetMapping("/widgets")
		public List<Widget> getWidgets(@RequestParam(name = "exactMatch", required = false) String exactMatch,
				@RequestParam(name = "name", required = false) String name) {

			String query = "SELECT * FROM widgets";
			if (name != null) {
				query += " where name ";
				if ("true".equals(exactMatch)) {
					query += "= '" + name + "'";
				}
				else {
					query += "like '%" + name + "%'";
				}
			}

			List<Widget> widgets = new ArrayList<>();

			try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query)) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String theName = rs.getString("name");
					String category = rs.getString("category");
					BigDecimal price = rs.getBigDecimal("price");
					Date purchaseDate = rs.getDate("purchase_date");

					Widget w = new Widget();
					w.setName(theName);
					w.setCategory(category);
					w.setPrice(price);
					w.setPurchaseDate(purchaseDate);

					widgets.add(w);
				}
			} catch (SQLException e) {
				System.out.println(e);
				return widgets;
			}
			return widgets;
		}
	}
}
