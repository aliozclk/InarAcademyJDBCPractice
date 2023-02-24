package sqlHomework.runner.stepDefs;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import sqlHomework.runner.utils.DBUtils;

import java.util.List;
import java.util.Map;

public class DBStepDefs {

   //String col1, String col2, String col3, String col4, String col5
   @Given("the best customers name is {string}")
   public void the_best_customers_name_is_something(String name) {
       String query = "SELECT c.customer_id,CONCAT(c.first_name,' ',c.last_name) AS name,\n" +
               "ci.city,co.country,SUM(p.amount) AS total_purchase FROM payment p\n" +
               "INNER JOIN customer c ON c.customer_id = p.customer_id\n" +
               "INNER JOIN address a ON a.address_id = c.address_id\n" +
               "INNER JOIN city ci ON ci.city_id = a.city_id\n" +
               "INNER JOIN country co ON co.country_id = ci.country_id\n" +
               "GROUP BY c.customer_id, ci.city,co.country\n" +
               "ORDER BY total_purchase DESC";
       List<Map<String, Object>> resultMap = DBUtils.getQueryResultMap(query);
       String actualResult = resultMap.get(0).get("name").toString();
       System.out.println(actualResult);
       Assert.assertEquals(actualResult,name);
   }


    @Given("address of store2 {string}")
    public void address_of_store2_something(String address) {
        String query = "SELECT s.store_id AS store ,  a.address , c.city , co.country FROM store s\n" +
                "INNER JOIN address a ON a.address_id = s.address_id\n" +
                "INNER JOIN city c ON c.city_id = a.city_id\n" +
                "INNER JOIN country co ON co.country_id = c.country_id\n" +
                "WHERE s.store_id = 2";
        List<Map<String, Object>> resultMap = DBUtils.getQueryResultMap(query);
        String actualResult = resultMap.get(0).get("address").toString();
        System.out.println(actualResult);
        Assert.assertEquals(actualResult,address);
    }

    @Given("name of movie in the first row {string}")
    public void name_of_movie_in_the_first_row(String title){
        String query = "SELECT inventory.film_id, title, COUNT(*) AS num_copies\n" +
                "FROM inventory\n" +
                "INNER JOIN film ON inventory.film_id = film.film_id\n" +
                "WHERE store_id = 1\n" +
                "GROUP BY inventory.film_id, title\n" +
                "HAVING COUNT(*) = (\n" +
                "  SELECT MAX(copy_count)\n" +
                "  FROM (\n" +
                "    SELECT film_id, COUNT(*) AS copy_count\n" +
                "    FROM inventory\n" +
                "    WHERE store_id = 1\n" +
                "    GROUP BY film_id\n" +
                "  ) AS copy_counts\n" +
                ")\n" +
                "ORDER BY film_id;";
        List<Map<String, Object>> resultMap = DBUtils.getQueryResultMap(query);
        String actualResult = resultMap.get(0).get("title").toString();
        System.out.println(actualResult);
        Assert.assertEquals(actualResult,title);
    }



    @Given("the first row of total_sales : {string}")
    public void theFirstRowOfTotal_sales(String total_sales) {

        String query = "SELECT CONCAT(s.first_name,' ',s.last_name)AS name,\n" +
                "date_trunc('month', p.payment_date) AS month,\n" +
                "SUM(p.amount) AS total_sales FROM payment p\n" +
                "INNER JOIN staff s ON s.staff_id = p.staff_id\n" +
                "GROUP BY name,month";
        List<Map<String, Object>> resultMap = DBUtils.getQueryResultMap(query);
        String actualResult = resultMap.get(0).get("total_sales").toString();
        System.out.println(actualResult);
        Assert.assertEquals(actualResult,total_sales);
    }

    @Given("number of three copied movies in store two is : {string}")
    public void numberOfThreeCopiedMoviesInStoreIs( String count) {
       String query = "SELECT COUNT(*) FROM (SELECT COUNT(*), film_id FROM inventory\n" +
               "WHERE store_id = 2 \n" +
               "GROUP BY film_id\n" +
               "HAVING COUNT(*) = 3\n" +
               "ORDER BY film_id) AS store_2";
       List<Map<String,Object>> resultMap = DBUtils.getQueryResultMap(query);
       String actualResult = resultMap.get(0).get("count").toString();
        System.out.println(actualResult);
        Assert.assertEquals(actualResult,count);

    }
}
