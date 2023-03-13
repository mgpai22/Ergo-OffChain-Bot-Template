package execute
import configs.serviceOwnerConf
import node.BaseClient
import utils.network

class Client()
    extends BaseClient(
      nodeInfo = execute.DefaultNodeInfo(
        new network(
          serviceOwnerConf.read("serviceOwner.json").nodeUrl
        ).getNetworkType
      )
    ) {}
