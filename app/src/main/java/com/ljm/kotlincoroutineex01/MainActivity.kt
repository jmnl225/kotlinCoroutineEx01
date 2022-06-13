package com.ljm.kotlincoroutineex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.coroutineScope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main()

        main2()

        main3()

        main4()

        main5()
    }

    private fun main() = runBlocking{ //runBlocking: blocks the current thread until its completion
        // runBlocking 없이 launch를 하게되면 에러발생.
        launch{ //coroutine builder
            delay(1000L) // a suspending function: not block
            println("World!")
        }
        println("Hello")
    }



    /**
     * main() 함수 분해
     **/
    private fun main2() = runBlocking {
        launch{doWorld()}
        println("Hello")
    }

    private suspend fun doWorld(){ //블록함수를 suspend 라고 붙여서 사용가능
        //중단함수를 빼서 사용할경우 api가 불명확해질 수 있으므로 다른 클래스 안에 넣어서 사용하길 권장.
        delay(1000L)
        println("World!")
    }


    /**
     * Scope builder
     **/
    private fun main3() = runBlocking {
        doWorld3()
    }

    private suspend fun doWorld3() = coroutineScope{
        // 다른 코루틴스코프를 생성해서 호출이 가능하다.
        // 중단함수 안에 코루틴스코프 작성이 가능하다.
        launch {
            delay(1000L)
            println("World!")
        }
        println("Hello")
    }


    /**
     * Scope builder and concurrency
     */
    private fun main4() = runBlocking {
        doWorld4()
        println("Done")
    }

    private suspend fun doWorld4() = coroutineScope {
        //중단함수 안에 코루틴스코프를 여러개 만들어서 사용 가능
        //main4는 중단함수 안에 있는 코루틴스코프가 모두 완료된 이후 다음 줄을 진행
        launch {
            delay(2000L)
            println("World 2")
        }

        launch {
            delay(1000L)
            println("World 1")
        }
        print("Hello")

    }


    /**
     * An explicit job
     */
    private fun main5() = runBlocking{
        val job = launch{
            delay(1000L)
            println("World!")
        }

        println("Hello")
        job.join()
        println("Done")

        //실행결과
        // Hello
        // World!
        // Done
    }




}