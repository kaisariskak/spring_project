package kz.bsbnb.usci.receiver.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ClusterRespondent {

    CLUSTER_1(1),
    CLUSTER_2(2);

    private long id;

    ClusterRespondent(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static final Map<Integer, ClusterRespondent> map = new ConcurrentHashMap<>();

    static {
        for (ClusterRespondent clusterRespondent : ClusterRespondent.values()) {
            map.put((int)clusterRespondent.getId(), clusterRespondent);
        }
    }

    public static ClusterRespondent getClusterRespondent(int id) {
        return map.get(id);
    }

}
