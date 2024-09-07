package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * NamedParameterJdbcTemplate
 * SqlParameterSource
 * - BeanPropertySqlParameterSource
 * - MapSqlParameterSource
 * Map
 *
 * BeanPropertyRowMapper
 *
 */
@Slf4j
public class JdbcTemplateItemRepositoryV2 implements ItemRepository {
    // private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate template;

    public JdbcTemplateItemRepositoryV2(DataSource dataSource) {
        // this.template = new JdbcTemplate(dataSource);
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        // String sql = "INSERT INTO item(item_name, price, quantity) VALUES (?,?,?)";
        String sql = "INSERT INTO item(item_name, price, quantity) VALUES (:itemName, :price, :quantity)";

        // KeyHolder keyHolder = new GeneratedKeyHolder();
        // template.update(connection -> {
        //     //자동 증가 키
        //     PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
        //     ps.setString(1, item.getItemName());
        //     ps.setInt(2, item.getPrice());
        //     ps.setInt(3, item.getQuantity());
        //     return ps;
        // }, keyHolder);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new BeanPropertySqlParameterSource(item);
        template.update(sql, param, keyHolder);

        long key = keyHolder.getKey().longValue();
        item.setId(key);

        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        // String sql = "UPDATE item SET item_name=?, price=?, quantity=? WHERE id=?";
        String sql = "UPDATE item SET item_name=:itemName, price=:price, quantity=:quantity WHERE id=:id";

        SqlParameterSource param =
                new MapSqlParameterSource().addValue("itemName", updateParam.getItemName())
                                           .addValue("price", updateParam.getPrice())
                                           .addValue("quantity", updateParam.getQuantity())
                                           .addValue("id", itemId);

        // template.update(sql,
        //         updateParam.getItemName(),
        //         updateParam.getPrice(),
        //         updateParam.getQuantity(),
        //         itemId);
        template.update(sql, param);
    }

    @Override
    public Optional<Item> findById(Long id) {
        // String sql = "SELECT id, item_name, price, quantity FROM item WHERE id = ?";
        String sql = "SELECT id, item_name, price, quantity FROM item WHERE id=:id";

        try {
            // Item item = template.queryForObject(sql, itemRowMapper(), id);
            Map<String, Object> param = Map.of("id", id);
            Item item = template.queryForObject(sql, param, itemRowMapper());

            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        SqlParameterSource param = new BeanPropertySqlParameterSource(cond);

        String sql = "SELECT id, item_name, price, quantity FROM item";
        //동적 쿼리
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " WHERE";
        }

        boolean andFlag = false;
        // List<Object> param = new ArrayList<>();
        if (StringUtils.hasText(itemName)) {
            // sql += " item_name LIKE concat('%',?,'%')";
            sql += " item_name LIKE CONCAT('%',:itemName,'%')";
            // param.add(itemName);
            andFlag = true;
        }

        if (maxPrice != null) {
            if (andFlag) {
                sql += " AND";
            }
            // sql += " price <= ?";
            sql += " price <= :maxPrice";
            // param.add(maxPrice);
        }

        log.info("sql={}", sql);

        // return template.query(sql, itemRowMapper(), param.toArray());
        return template.query(sql, param, itemRowMapper());
    }

    private RowMapper<Item> itemRowMapper() {
        // return ((rs, rowNum) -> {
        //     Item item = new Item();
        //     item.setId(rs.getLong("id"));
        //     item.setItemName(rs.getString("item_name"));
        //     item.setPrice(rs.getInt("price"));
        //     item.setQuantity(rs.getInt("quantity"));
        //     return item;
        // });
        return BeanPropertyRowMapper.newInstance(Item.class); //camel 변환 지원
    }
}
