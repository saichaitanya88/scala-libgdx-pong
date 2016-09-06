package core

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class Paddle(val rect: Rectangle,val leftKeyCode: Int,val rightKeyCode: Int) {
  val resetPos = new Rectangle(rect.x, rect.y, rect.width, rect.height)
  def update(){
    if (Gdx.input.isKeyPressed(leftKeyCode)){
      rect.x -= 4
    }
    if (Gdx.input.isKeyPressed(rightKeyCode)){
      rect.x += 4
    }
    // Clamp paddles
    rect.x = Math.min(Math.max(rect.x,0),Gdx.graphics.getWidth-rect.getWidth)
  }
  def reset(){
    rect.x = resetPos.x
    rect.y = resetPos.y
  }
  def draw(shapeRenderer: ShapeRenderer){
    shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height)
  }
}