import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.webkit.WebView
import kotlin.math.abs

class AccessibleWebView(context: Context) : WebView(context) {
    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1 == null) return false
            val deltaX = e2.x - e1.x
            if (abs(deltaX) > abs(e2.y - e1.y)) {
                if (deltaX > 0) {
                    // Right swipe: Focus on the next paragraph
                    evaluateJavascript(
                        """
                        (function() {
                            const paragraphs = document.querySelectorAll('p');
                            let focusedIndex = -1;
                            for (let i = 0; i < paragraphs.length; i++) {
                                if (document.activeElement === paragraphs[i]) {
                                    focusedIndex = i;
                                    break;
                                }
                            }
                            if (focusedIndex < paragraphs.length - 1) {
                                paragraphs[focusedIndex + 1].focus();
                                return paragraphs[focusedIndex + 1].textContent;
                            }
                            return null;
                        })();
                        """.trimIndent()
                    ) { result ->
                        result?.let { announceForAccessibility(it) }
                    }
                } else {
                    // Left swipe: Focus on the previous paragraph
                    evaluateJavascript(
                        """
                        (function() {
                            const paragraphs = document.querySelectorAll('p');
                            let focusedIndex = -1;
                            for (let i = 0; i < paragraphs.length; i++) {
                                if (document.activeElement === paragraphs[i]) {
                                    focusedIndex = i;
                                    break;
                                }
                            }
                            if (focusedIndex > 0) {
                                paragraphs[focusedIndex - 1].focus();
                                return paragraphs[focusedIndex - 1].textContent;
                            }
                            return null;
                        })();
                        """.trimIndent()
                    ) { result ->
                        result?.let { announceForAccessibility(it) }
                    }
                }
                return true
            }
            return false
        }
    })

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { gestureDetector.onTouchEvent(it) }
        return super.onTouchEvent(event)
    }
}