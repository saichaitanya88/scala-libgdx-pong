package core

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Ellipse
import sun.java2d.pipe.TextRenderer
import com.badlogic.gdx.graphics.g2d.BitmapFont

class MyGdxGame extends ApplicationAdapter{
  var shapeRenderer: ShapeRenderer = null
	var ball: Ball = null
	var paddle1: Paddle = null
	var paddle2: Paddle = null
	var wall1: Wall = null
	var wall2: Wall = null
	
	var score1: Int = 0
	var score2: Int = 0
	
  private var bFont: BitmapFont = null
  private var spriteBatch: SpriteBatch = null

	
	override def create () { 
    spriteBatch = new SpriteBatch
	  shapeRenderer = new ShapeRenderer
	  bFont = new BitmapFont
	  wall1 = new Wall(new Rectangle(0f,0f,10f,Gdx.graphics.getHeight))
	  wall2 = new Wall(new Rectangle(Gdx.graphics.getWidth-10f,0f,10f,Gdx.graphics.getHeight))
	  paddle1 = new Paddle(new Rectangle(-50 + Gdx.graphics.getWidth/2, 0f,100f,10f), Input.Keys.A, Input.Keys.D)
	  paddle2 = new Paddle(new Rectangle(-50 + Gdx.graphics.getWidth/2, Gdx.graphics.getHeight-10f,100f,10f), Input.Keys.LEFT, Input.Keys.RIGHT)
	  ball = new Ball(new Ellipse(-10 + Gdx.graphics.getWidth/2,-10 + Gdx.graphics.getHeight/2,20f,20f), wall1, wall2, paddle1, paddle2)
	}

	override def render () {
	  ball.update()
	  paddle1.update()
	  paddle2.update()
	  if (ball.ellipse.y < 0){
	    score1 += 1
	    ball.reset()
	    paddle1.reset()
	    paddle2.reset()
	  }
	  if (ball.ellipse.y > Gdx.graphics.getHeight){
	    score2 += 1
	    ball.reset()
	    paddle1.reset()
	    paddle2.reset()
	  }
	  
		Gdx.gl.glClearColor(0, 0, 0, 1)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
		wall1.draw(shapeRenderer)
		wall2.draw(shapeRenderer)
		shapeRenderer.end()
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
	  ball.draw(shapeRenderer)
		paddle1.draw(shapeRenderer)
		paddle2.draw(shapeRenderer)
		shapeRenderer.end()
		
		spriteBatch.begin()
		bFont.draw(spriteBatch, s"Score: ${score1}-${score2}", Gdx.graphics.getWidth - 100, Gdx.graphics.getHeight/2)
		spriteBatch.end()
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
		  Gdx.app.exit()
		}
	}
	
	override def dispose () {
		shapeRenderer.dispose()
	}
}