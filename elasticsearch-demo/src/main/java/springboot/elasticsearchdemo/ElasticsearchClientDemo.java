package springboot.elasticsearchdemo;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElasticsearchClientDemo {
    private static String CLUSTER_NAME = "elasticsearch_xuekaiyuan";
    private static String HOST_IP = "localhost";
    private static int TCP_PORT = 9300;

    public static void main(String[] args) throws UnknownHostException {
        List<Integer> collect = Arrays.stream(new int[]{2, 3, 4, 1, 2, 4, 5, 7, 4, 2, 1, 2, 5}).mapToObj(Integer::new).collect(Collectors.toList());
        mySort(collect,0,collect.size()-1);
        System.out.println(collect);
    }

    public static void mySort(List<Integer> list,int begin,int end){
        if(begin>=end) return;
        int tmp = partition(list,begin,end);
        mySort(list,begin,tmp-1);
        mySort(list,tmp+1,end);
    }

    public static int partition(List<Integer> list,int begin,int end){
        if(begin==end) return begin;
        int tmp = list.get(begin);
        int i =begin,j=end;
        while(i>=begin&&j<=end){
            while(i<=end&&list.get(i)<=tmp){
                i++;
            }
            while(j>=begin&&list.get(j)>tmp){
                j--;
            }
            if(j<i){
                int tt = list.get(j);
                list.set(begin,tt);
                list.set(j,tmp);
                return j;
            }

            int t = list.get(i);
            list.set(i,list.get(j));
            list.set(j,t);
            i++;
            j--;

        }
        return -1;
    }



}
