package clients.GraphQL;

import com.graphql.java.client.GraphQLClient;
import com.graphql.java.client.GraphQLClientBuilder;
import com.graphql.java.client.GraphQLRequest;
import com.graphql.java.client.GraphQLResponse;

public class GraphQLClient {

    private final GraphQLClient graphQLClient;

    public GraphQLClient() {
        graphQLClient = new GraphQLClient();
    }

    public void GraphQLClientExample(String graphQLAPIEndpointURL) {
        graphQLClient = GraphQLClientBuilder.newClient(graphQLAPIEndpointURL).build();
    }

    public GraphQLResponse executeQuery(String query) {
        GraphQLRequest graphQLRequest = new GraphQLRequest(query);
        return graphQLClient.execute(graphQLRequest);
    }

    public static void main(String[] args) {
        GraphQLClientExample graphQLClientExample = new GraphQLClientExample("https://graphql.example.com");

        // Execute a GraphQL query
        GraphQLResponse graphQLResponse = graphQLClientExample.executeQuery("{ hello }");

        // Parse the GraphQL response
        String hello = graphQLResponse.getData().get("hello").toString();

        // Print the result
        System.out.println(hello);
    }
}