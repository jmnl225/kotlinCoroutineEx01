package com.ljm.kotlincoroutineex01

class TestDispatchers {
    //테스트를 목적으로 하는 CoroutineDispatcher 구현
    //새 코루틴의 실행을 예측할 수 있도록 테스트 중에 새 코루틴을 만드는 경우 테스트디스패처를 사용.
    //테스트 디스패처의 구현방식
    //1.  StandardTestDispatcher
    //2.  UnconfinedTestDispatcher
    // - 둘 다 TestCoroutineScheduler를 사용하여 가상시간을 제어, 테스트 내의 실행중인 코루틴을 관리한다.

    // 테스트에서 사용하는 스케줄러 인스턴스는 하나만 있어야함.


}