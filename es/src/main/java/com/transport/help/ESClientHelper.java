//package com.transport.help;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.util.StringUtils;
//
//import java.net.InetAddress;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// *
// * ESClientHelper 按照Elasticsearch API，在Java端使用是ES服务需要创建Java
// * Client，但是每一次连接都实例化一个client，对系统的消耗很大， 即使在使用完毕之后将client
// * close掉，由于服务器不能及时回收socket资源，极端情况下会导致服务器达到最大连接数。为了解决上述问题并提高client利用率，可以参考使用池化技术复用client。
// *
// * @author xujin
// *
// */
//public class ESClientHelper {
//
//    private static final Log logger = LogFactory.getLog(ESClientHelper.class);
//    private static ESClientHelper instance;
//    private Settings setting;
//    private Map<String, Client> clientMap = new ConcurrentHashMap<String, Client>();
//    // HostName与Port
//    private Map<String, Integer> ips = new HashMap<String, Integer>();
//    private String clusterName = IEsConfig.ES_CONFIG.getCluster();
//    public static synchronized ESClientHelper getInstance() {
//        if (instance == null) {
//            instance = new ESClientHelper();
//        }
//        return instance;
//    }
//    private ESClientHelper() {
//        init();
//    }
//    /**
//     * 初始化默认的client
//     */
//    public void init() {
//        String address = Configs.ES_CONFIG.getAddress();
//        if (StringUtils.isNotEmpty(address)) {
//            StringTokenizer stokenizer = new StringTokenizer(address, ",");
//            while (stokenizer.hasMoreTokens()) {
//                String ip = stokenizer.nextToken();
//                ips.put(ip, Configs.ES_CONFIG.getPort());
//            }
//        }
//        setting = Settings.builder().put("cluster.name", Configs.ES_CONFIG.getCluster()).build();
//        addClient(setting, getAllAddress(ips));
//    }
//    /**
//     * 获得所有的地址端口
//     *
//     * @return
//     */
//    public List<TransportAddress> getAllAddress(Map<String, Integer> ips) {
//        List<TransportAddress> addressList = new ArrayList<TransportAddress>();
//        for (String ip : ips.keySet()) {
//            try {
//                addressList.add(new TransportAddress(InetAddress.getByName(ip), ips.get(ip)));
//            } catch (Exception e) {
//                logger.error(" add InetSocketTransportAddress ");
//            }
//        }
//        return addressList;
//    }
//    public Client getClient() {
//        return getClient(clusterName);
//    }
//    public Client getClient(String clusterName) {
//        return clientMap.get(clusterName);
//    }
//    public void addClient(Settings setting, List<TransportAddress> transportAddress) {
//        Client client = new PreBuiltTransportClient(setting).addTransportAddresses(
//                transportAddress.toArray(new TransportAddress[transportAddress.size()]));
//
//        clientMap.put(setting.get("cluster.name"), client);
//    }
//}