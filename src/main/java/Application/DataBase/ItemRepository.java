package Application.DataBase;

import Application.Entities.Item;
import Application.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {

    @Autowired
    private final JdbcTemplate jdbc;

    public ItemRepository(JdbcTemplate jdbc) {
            this.jdbc = jdbc;
    }

    public List<Integer> getUserItems(User user) throws SQLException {
        return jdbc.query("SELECT * FROM user_to_items WHERE user_id = " + user.getUserId(),
                this::rowToIntegerList);
    }

    public List<Item> getAllItems() throws SQLException {
        return getItems("");
    }

    public List<Item> getItemsByType(String type) throws SQLException {
        return getItems(type);
    }

    public void addNewItemToUser(User user, Item item) {
        jdbc.update("INSERT INTO user_to_items (user_id, item_id) VALUES (" + user.getUserId()
                + ", " + item.getItemId() + ")");
    }

    public Item getItemById(int itemId) throws SQLException {
        return jdbc.query("SELECT * FROM items WHERE id = " + itemId, this::rowToItem);
    }

    private Item rowToItem(ResultSet resultSet) throws SQLException {
        if (resultSet.next())
            return createItem(resultSet);
        return new Item();
    }

    private List<Item> getItems(String type) {
        return jdbc.query("SELECT * FROM items " +
                (type.isEmpty() ? "" : "WHERE type = '" + type + "'"), this::rowToItemList);
    }

    private List<Integer> rowToIntegerList(ResultSet resultSet) throws SQLException {
        List<Integer> itemIdentifiers = new ArrayList<>();
        do {
            itemIdentifiers.add(resultSet.getInt("item_id"));
        } while (resultSet.next());
        return itemIdentifiers;
    }

    private List<Item> rowToItemList(ResultSet resultSet) throws SQLException {
        List<Item> items = new ArrayList<>();
        do {
            items.add(createItem(resultSet));
        } while (resultSet.next());
        return items;
    }

    private Item createItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setItemId(resultSet.getInt("id"));
        item.setCost(resultSet.getInt("cost"));
        item.setType(resultSet.getString("type"));
        item.setLink(resultSet.getString("link"));
        return item;
    }
}