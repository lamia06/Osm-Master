package io.github.joxit.osm.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import io.github.joxit.osm.model.Tile;
import io.github.joxit.osm.service.TileService;
import mil.nga.sf.geojson.GeoJsonObject;
import org.checkerframework.checker.units.qual.K;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * C'est le controlleur de l'application, il faut le déclarer comme tel et activer les CrossOrigin
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */

@RestController
@CrossOrigin
@RequestMapping({"", "/"})
public class TileController {
  private static Logger LOGGER = LoggerFactory.getLogger(TileController.class);
  @Autowired
  private TileService tileService;


  /**
   * Cette méthode est le point d'entrée de l'API, il prend les requêtes au format `/{z}/{x}/{y}.png`.
   * Attention, il doit renvoyer le header Content-Type image/png; voir les MediaType de Spring
   *
   * @param z zoom
   * @param x coordonée
   * @param y coordonée
   * @return l'image au format PNG
   */
  @RequestMapping(
          value = "/{z}/{x}/{y}.png",
          method = RequestMethod.GET,
          produces= {MediaType.IMAGE_JPEG_VALUE}
  )
  @ResponseBody
  public byte[] getTile(@PathVariable int z,@PathVariable int x,@PathVariable int y) {
    Tile tile = new Tile(z,x,y);

    return tileService.getTile(tile);
  }

  /**
   * Cette méthode est le point d'entrée des préfectures, il prend les requêtes sur l'entrée `/prefectures.geojson`.
   * Attention, il doit renvoyer le header Content-Type application/json
   *
   * @return String representant le GeoJSON des prefectures
   */

  @RequestMapping(
          value = "/prefectures.geojson",
          method = RequestMethod.GET,
          produces={MediaType.APPLICATION_JSON_VALUE}
  )
  @ResponseBody
  public String getPrefectures() throws IOException {
    String prefectures = tileService.getPrefectures();
    return prefectures;
  }

  /**
   * Cette méthode est le point d'entrée de l'API POIs sous persistence, il prend les requêtes sur l'entrée `/pois.geojson`.
   * Attention, il doit renvoyer le header Content-Type application/json
   *
   * @return le geojson des POIs à renvoyer
   */
  @RequestMapping(
          value = "/pois.geojson",
          method = RequestMethod.GET,
          produces={MediaType.APPLICATION_JSON_VALUE}
  )
  @ResponseBody
  public GeoJsonObject getPOIs() {

    return tileService.getPOIs();
  }
}
