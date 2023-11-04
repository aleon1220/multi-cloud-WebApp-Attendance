package service.clients.GraphQL;

import org.springframework.web.reactive.function.client.WebClient;
import com.graphql_java_generator.client.GraphQLConfiguration;
import com.graphql_java_generator.client.GraphQLWebClient;

public class GraphQLClient {

    public static void main(String[] args) {
        // Create a WebClient
        WebClient webClient = WebClient.create("http://localhost:8080/graphql");

        // Create a GraphQLConfiguration
        GraphQLConfiguration graphQLConfiguration = new GraphQLConfiguration(webClient);

        // Create a GraphQLWebClient
        GraphQLWebClient graphQLWebClient = new GraphQLWebClient(graphQLConfiguration);

        // Define a query
        String query = "{ helloWorld }";

        // Execute the query
        String response = graphQLWebClient.executeQuery(query);

        // Print the response
        System.out.println(response);
    }
}