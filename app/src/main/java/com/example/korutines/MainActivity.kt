package com.example.korutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import java.nio.channels.Channel
import kotlin.coroutines.CoroutineContext

class MainActivity() : AppCompatActivity() {
    lateinit var q:String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Timurandrweb

        //Dispatchers.Main - главный поток
        //launch(Dispatchers.Main) посылает Runnable в Handler, так что его код выполняется не сразу.
        //launch(Dispatchers.Main, CoroutineStart.UNDISPATCHED) немедленно выполнит свое лямбда-выражение в текущем потоке.
/*
        val job: Job = GlobalScope.launch(context = Dispatchers.Main) {

            var result = withContext(Dispatchers.Default){//это уже не главный поток!
                //Default - не требует сложных операций (матиматические вычесления)
                //IO для ввода вывода и слжных задач (выход в сеть или бд)

                //ассинхронные потоки (выполняются вместе - одновременно!)
                val potok1 = async{
                    return@async "1 асинхронный поток выполнился"
                }
                val potok2 = async{
                    return@async "2 асинхронный поток выполнился"
                }

                //await() - означает ждём блокируем до завершения операции
                val res1 = potok1.await()
                val res2 = potok2.await()

                return@withContext "$res1+\n+$res2"
            }
            Log.d("jj ",result)
        }
        job.start()
        //job.cancel()
        //job.join() //приостанавливает выполнение актуальной корутины, пока задача не будет выполнена
*/


        /////////////////////////////////////

        val job = SupervisorJob()
        val scope = CoroutineScope(Dispatchers.Default + job)


        // может вызвать исключение
        suspend fun doWork(): Deferred<String> = coroutineScope {     // (1)
            async {
                "qq"
            }
        }

        fun loadData() = scope.launch {                       // (2)
            try {
                val a = doWork().await()
                Log.d("kd",a.toString())
            } catch (e: Exception) {

            }
        }

        ///////////////////////////////////////


        val workManager = WorkManager()
        workManager.doWork1()
        workManager.doWork2()
        workManager.cancelAllWork()//отмена





    }


    override fun onDestroy() {
        super.onDestroy()

    }


}