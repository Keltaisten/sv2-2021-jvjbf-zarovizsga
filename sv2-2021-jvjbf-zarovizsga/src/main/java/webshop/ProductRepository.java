package webshop;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;

public class ProductRepository {
    DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO `products` (product_name, price, stock) VALUES(?, ?, ?);",
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, productName);
            stmt.setInt(2, price);
            stmt.setInt(3, stock);
            stmt.executeUpdate();
            return getGeneratedKey(stmt);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot insert product", sqle);
        }
    }

    private long getGeneratedKey(PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            throw new IllegalStateException("Cannot get id");
        }
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM `products` WHERE `id` = ?;", (rs, rowNum) ->
                new Product(
                        rs.getLong("id"),
                        rs.getString("product_name"),
                        rs.getInt("price"),
                        rs.getInt("stock")), id);
    }

    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update("UPDATE products SET stock = stock - ? WHERE id = ?;", amount, id);
    }
}
