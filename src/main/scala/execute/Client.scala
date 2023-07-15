package execute
import configs.serviceOwnerConf
import node.BaseClient
import utils.network

class Client(
    nodeUrl: String = serviceOwnerConf.read("serviceOwner.json").nodeUrl
) extends BaseClient(
      nodeInfo = execute.DefaultNodeInfo(
        new network(nodeUrl).getNetworkType
      )
    ) {}
