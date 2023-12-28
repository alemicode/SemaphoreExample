package com.alemicode.semaphoreExample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alemicode.semaphoreExample.ui.theme.SemaphoreExampleTheme
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import java.util.concurrent.Semaphore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            SemaphoreExampleTheme {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(modifier = Modifier.fillMaxSize()) {
                        Button(onClick = {
                            increaseNumber()
                        }) {
                            Text(text = "Increment")
                        }
                    }
                }

            }


        }
    }

    fun increaseNumber() {
        val s = Semaphore(2000)

        val t1 = Thread(Runnable
        {
            s.acquireUninterruptibly()
            Log.d("TAG", "aaa: t1")
            Thread.sleep(2000)
            s.release()
        })

        val t2 = Thread(Runnable
        {
            s.acquireUninterruptibly()
            Log.d("TAG", "aaa: t2")
            Thread.sleep(2000)
            s.release()
        })

        val t3 = Thread(Runnable
        {
            s.acquireUninterruptibly()
            Log.d("TAG", "aaa: t3")
            Thread.sleep(2000)
            s.release()
        })

        val t4 = Thread(Runnable
        {
            s.acquireUninterruptibly()
            Log.d("TAG", "aaa: t4")
            Thread.sleep(2000)
            s.release()
        })


        t1.start()
        t2.start()
        t3.start()
        t4.start()
    }

}



