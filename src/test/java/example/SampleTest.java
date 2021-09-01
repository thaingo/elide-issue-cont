package example;

import com.yahoo.elide.core.exceptions.HttpStatus;
import com.yahoo.elide.spring.controllers.JsonApiController;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.attr;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.attributes;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.data;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.datum;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.id;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.resource;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.type;
import static org.hamcrest.Matchers.equalTo;

class SampleTest extends IntegrationTest {

    @Test
    @Sql(statements = {
            "INSERT INTO sample (id, email, owner) VALUES ('1', 'email@1', 'owner1');"
    })
    void jsonApiGetTest() {
        when()
                .get("/api/v1/sample")
                .then()
                .body(equalTo(
                        data(
                                resource(
                                        type("sample"),
                                        id("1"),
                                        attributes(
                                                attr("address", null),
                                                attr("email", "email@1"),
                                                attr("owner", "owner1")
                                        )
                                )
                        ).toJSON())
                )
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Sql(statements = {
            "DELETE FROM sample;"
    })
    void jsonApiPostTest() {
        final Map<String, String> addressValue = new LinkedHashMap<>();
        addressValue.put("city", "HCM");
        addressValue.put("country", "VN");
        given()
                .contentType(JsonApiController.JSON_API_CONTENT_TYPE)
                .body(
                        datum(
                                resource(
                                        type("sample"),
                                        id("1"),
                                        attributes(
                                                attr("email", "email@1"),
                                                attr("owner", "owner1"),
                                                attr("address", addressValue)
                                        )
                                )
                        )
                )
                .when()
                .post("/api/v1/sample")
                .then()
                .body(equalTo(datum(
                        resource(
                                type("sample"),
                                id("1"),
                                attributes(
                                        attr("address", addressValue),
                                        attr("email", "email@1"),
                                        attr("owner", "owner1")
                                )
                        )
                ).toJSON()))
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @Sql(statements = {
            "DELETE FROM sample;",
            "INSERT INTO sample (id, email, owner, city, country) VALUES\n" +
                    "\t\t('1','email@1','owner1', 'HCM', 'VN');"
    })
    void jsonApiPatchTest() {
        final Map<String, String> addressValue1 = new LinkedHashMap<>();
        addressValue1.put("city", "Saigon");
        final Map<String, String> addressValue2 = new LinkedHashMap<>();
        addressValue1.put("city", "Saigon");
        addressValue1.put("country", "VN");
        given()
                .contentType(JsonApiController.JSON_API_CONTENT_TYPE)
                .body(
                        datum(
                                resource(
                                        type("sample"),
                                        id("1"),
                                        attributes(
                                                attr("address", addressValue1)
                                        )
                                )
                        )
                )
                .when()
                .patch("/api/v1/sample/1")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        when()
                .get("/api/v1/sample")
                .then()
                .body(equalTo(
                        data(
                                resource(
                                        type("sample"),
                                        id("1"),
                                        attributes(
                                                attr("address", addressValue2),
                                                attr("email", "email@1"),
                                                attr("owner", "owner1")
                                        )
                                )
                        ).toJSON())
                )
                .statusCode(HttpStatus.SC_OK);
    }

}