package com.ljm.kotlincoroutineex01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*


class TestDispatchers: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testDispatcherMain()

    }

    private fun testDispatcherMain(){
        //테스트를 목적으로 하는 CoroutineDispatcher 구현
        //새 코루틴의 실행을 예측할 수 있도록 테스트 중에 새 코루틴을 만드는 경우 테스트디스패처를 사용.
        //테스트 디스패처의 구현방식
        //1.  StandardTestDispatcher
        //2.  UnconfinedTestDispatcher
        // - 둘 다 TestCoroutineScheduler를 사용하여 가상시간을 제어, 테스트 내의 실행중인 코루틴을 관리한다.

        // 테스트에서 사용하는 스케줄러 인스턴스는 하나만 있어야함.


        /**
         * StandardTestDispatcher
         */
        @Test
        fun standardTest() = runTest {
            val userRepo = UserRepository()

            launch{userRepo.register("Alice")}
            launch { userRepo.register("Bob") }

            assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // Fails
        }
    }

    class UserRepository {
        private var users = mutableListOf<String>()

        suspend fun register(name: String) {
            delay(100L)
            users += name
            println("Registered $name")
        }

        suspend fun getAllUsers(): List<String> {
            delay(100L)
            return users
        }
    }

}