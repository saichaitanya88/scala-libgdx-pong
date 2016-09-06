package core

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Ellipse
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.Gdx

class Ball(val ellipse: Ellipse,val wall1: Wall,val wall2: Wall,val paddle1: Paddle, val paddle2: Paddle) {
  val resetEllipse = new Ellipse(ellipse.x, ellipse.y, ellipse.width, ellipse.height)
  val random = scala.util.Random
  var startSpeed = 5f
  val movementVector = new Vector2(-1.0f,startSpeed)
  var elapsedTime: Float = 0
  def update(){
    ellipse.x = (ellipse.x + (movementVector.x))
    ellipse.y = (ellipse.y + (movementVector.y))
    val boundingRect = new Rectangle(ellipse.x, ellipse.y, ellipse.width, ellipse.height)
    
    if (boundingRect.overlaps(wall1.rect) || boundingRect.overlaps(wall2.rect)){
      movementVector.x = movementVector.x * -1
    }
    
    if (boundingRect.overlaps(paddle1.rect) || boundingRect.overlaps(paddle2.rect)){
      movementVector.y = movementVector.y * -1
      movementVector.x = (random.nextInt(100) - 50) / 10
      // Prevent delayed collision detection from causing ball to get stuck in paddle
      if (boundingRect.overlaps(paddle1.rect)) {
        ellipse.y = 30
      } else {
        ellipse.y = Gdx.graphics.getHeight - (30) 
      }
    }
    
    //speedup
    elapsedTime += Gdx.graphics.getDeltaTime()
    if (elapsedTime > 2){
      elapsedTime = 0
      movementVector.y += 1
      println("Speeding up!")
    }
  }
  def reset(){
    ellipse.x = resetEllipse.x
    ellipse.y = resetEllipse.y
    movementVector.x = 0
    movementVector.y = startSpeed
    elapsedTime = 0
  }
  def draw(shapeRenderer: ShapeRenderer){
    shapeRenderer.ellipse(ellipse.x, ellipse.y, ellipse.width, ellipse.height)
  }
}  