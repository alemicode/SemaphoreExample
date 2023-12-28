package com.alemicode.semaphoreExample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alemicode.semaphoreExample.ui.theme.SemaphoreExampleTheme
import java.util.concurrent.Semaphore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var reportCounter: String by remember {
                mutableStateOf("")
            }
            SemaphoreExampleTheme {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            reportCounter = ""
                            increaseNumber {
                                reportCounter += "\n $it"
                            }
                        }) {
                            Text(text = "Increment")
                        }

                        Text(text = "$reportCounter")
                    }
                }

            }


        }
    }

    private fun increaseNumber(showReport: (String) -> Unit) {
        var counter = 0
        val semaphore = Semaphore(1)

        val t1 = Thread(Runnable
        {
            semaphore.acquireUninterruptibly()
            showReport("------starting T1 ------")
            counter++
            showReport("t1 ${counter}")

            counter--
            showReport("t1 ${counter}")
            showReport("------END T1 ------ \n")



            semaphore.release()
        })

        val t2 = Thread(Runnable
        {
            semaphore.acquireUninterruptibly()
            showReport("------starting T2 ------")
            counter++
            showReport("t2 ${counter}")

            counter--
            showReport("t2 ${counter}")
            showReport("------END T2 ------ \n")

            semaphore.release()
        })

        val t3 = Thread(Runnable
        {
            semaphore.acquireUninterruptibly()
            showReport("------starting T3 ------")
            counter++
            showReport("t3 ${counter}")

            counter--
            showReport("t3 ${counter}")
            showReport("------END T3 ------ \n")

            semaphore.release()
        })

        val t4 = Thread(Runnable
        {
            semaphore.acquireUninterruptibly()
            showReport("------starting T4 ------")
            counter++
            showReport("t4 ${counter}")

            counter--
            showReport("t4 ${counter}")
            showReport("------END T4 ------ \n")

            semaphore.release()
        })


        t1.start()
        t2.start()
        t3.start()
        t4.start()
    }

}



