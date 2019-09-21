package springboot.elasticsearchdemo;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticsearchClientDemo {
    private static String CLUSTER_NAME = "elasticsearch_xuekaiyuan";
    private static String HOST_IP = "localhost";
    private static int TCP_PORT = 9300;

    public static void main(String[] args) throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name",CLUSTER_NAME)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(HOST_IP),TCP_PORT));
        GetResponse getResponse = client.prepareGet("books","IT","1").get();
        System.out.println(getResponse.getSourceAsString());
    }


}
