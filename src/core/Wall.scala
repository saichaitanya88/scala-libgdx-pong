package core

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.FixtureDef

class Wall(val rect: Rectangle) { 
  def draw(shapeRenderer: ShapeRenderer){
    shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height)
  }
}