package org.arquillian.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.modules.maven.ArtifactCoordinates;
import org.jboss.modules.maven.MavenResolver;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class WildFlyArquillianTest {
    @Deployment
    public static WebArchive createDeployment() throws IOException {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, JaxrsActivator.class.getPackage())
                .addAsLibraries(
                        MavenResolver
                                .createDefaultResolver()
                                .resolveJarArtifact(ArtifactCoordinates.fromString("org.assertj:assertj-core:3.21.0"))
                );
    }

    @Inject
    MessageService messageService;

    @Test
    public void cdiBeanTest() {
        assertThat(messageService.format("Hello World"))
                .isEqualTo("★★★ Hello World ★★★");
    }

    @ArquillianResource
    URL deploymentUrl;

    String resourcePrefix = JaxrsActivator.class.getAnnotation(ApplicationPath.class).value();

    // restassured が jaxb に依存していて jakarta ee 対応されていない
//    @Test
//    @RunAsClient
//    public void jaxrsResourceTest() {
//        System.out.println("deploymentUrl=" + deploymentUrl + ", resourcePrefix=" + resourcePrefix);
//
//        given()
//                .queryParam("message", "Hello World")
//                .when()
//                .get(String.format("%s%s%s", deploymentUrl, resourcePrefix, "echo"))
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body(is("★★★ Hello World ★★★"));
//
//        Map<String, String> body = new HashMap<>();
//        body.put("message", "Hello World");
//        given()
//                .contentType(ContentType.JSON)
//                .body(body)
//                .when()
//                .post(String.format("%s%s%s", deploymentUrl, resourcePrefix, "echo"))
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body("message", is("★★★ Hello World ★★★"));
//    }
}
