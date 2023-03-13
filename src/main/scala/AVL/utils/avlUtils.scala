package AVL.utils

import configs.{AVLJsonHelper, AvlJson}

import io.getblok.getblok_plasma.collections.{PlasmaMap}

import scala.collection.mutable

object avlUtils {

  def exportAVL[K, V](map: PlasmaMap[K, V]): AVLJsonHelper = {

    val manifest: io.getblok.getblok_plasma.collections.Manifest =
      map.getManifest(255)

    val manifestHex: String = manifest.toHexStrings._1

    val manifestDigestHex: String =
      manifest.digest.map("%02x".format(_)).mkString
    val manifestSubTreeHex: Seq[String] =
      manifest.toHexStrings._2

    new AVLJsonHelper(
      manifestHex,
      manifestDigestHex,
      manifestSubTreeHex.toArray
    )
  }

  def AVLFromExport[K, V](
      jsonData: AvlJson,
      map: PlasmaMap[K, V]
  ): Unit = {

    val manifest: io.getblok.getblok_plasma.collections.Manifest =
      io.getblok.getblok_plasma.collections.Manifest.fromHexStrings(
        jsonData.digestHex,
        jsonData.manifestHex,
        jsonData.subTreeHex.toSeq
      )

    map.loadManifest(manifest)

  }

}
