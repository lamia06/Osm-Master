package io.github.joxit.osm.service;

import io.github.joxit.osm.model.Tile;
import io.github.joxit.osm.utils.Svg;
import mil.nga.sf.geojson.GeoJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.w3c.dom.Document;
import mil.nga.sf.geojson.GeoJsonObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.beans.JavaBean;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


import static io.github.joxit.osm.utils.Svg.svgToPng;

/**
 * Service pour retourner les tuiles.
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */

@Service
public class TileService {




  private Document tile ;

  private Svg svg;

  /**
   * Ici il faut prendre les coordonnées de la tuile et renvoyer la donnée PNG associée.
   * Vous pouvez y ajouter des fonctionnalités en plus pour améliorer les perfs.
   *
   * @param tile qu'il faut renvoyer
   * @return le byte array au format png
   */
  public byte[] getTile(Tile tile) {
    if(tile.getZ()<0 || tile.getX()<0 || tile.getY()<0 || tile.getZ() >24
            || tile.getX()>Math.pow(2,tile.getZ()) || tile.getY()>Math.pow(2,tile.getZ())){
      return null;
    }

    return svg.getTile(tile);
  }

  /**
   * @return le contenu du fichier prefectures.geojson
   */
    public String getPrefectures() throws IOException {
      ClassPathResource cpr = new ClassPathResource("prefectures.geojson");
      BufferedReader rder = new BufferedReader(new InputStreamReader( cpr.getInputStream()));
     // String rsc = new String(FileCopyUtils.copyToString( rder ));

     // File file = resource.getFile();
      StringBuilder builder = new StringBuilder();
      try (rder) {
        String txt = null;
        while ((txt = rder.readLine()) != null)
          builder.append(txt);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return builder.toString();
 /*   //Tile tile = new Tile(z,x,y);

  */

            //svgToPng(getDocument(),256).map();

  }

  /**
   * Il faudra créer votre DAO pour récuperer les données.
   * Utilisez ce que vous voulez pour faire le DAO.
   *
   * @return les éléments contenus dans la base de données
   */
  public GeoJsonObject getPOIs() {
    return null;
  }
}
