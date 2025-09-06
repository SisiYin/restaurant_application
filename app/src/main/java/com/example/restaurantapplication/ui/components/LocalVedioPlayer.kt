package com.example.restaurantapplication.ui.components

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun LocalVideoPlayer(
    modifier: Modifier = Modifier,
    rawResId: Int,            // e.g. R.raw.restaurant
    autoPlay: Boolean = true,
    loop: Boolean = true,
    mute: Boolean = true,
    useController: Boolean = false
) {
    val context = LocalContext.current

    // 1) ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val uri: Uri = RawResourceDataSource.buildRawResourceUri(rawResId)
            setMediaItem(MediaItem.fromUri(uri))
            playWhenReady = autoPlay
            repeatMode = if (loop) REPEAT_MODE_ALL else Player.REPEAT_MODE_OFF
            volume = if (mute) 0f else 1f
            prepare()
        }
    }

    // 2) 真正的视图承载（Media3 PlayerView）
    AndroidView(
        modifier = modifier
        .clip(RoundedCornerShape(16.dp)),

    factory = {
            PlayerView(it).apply {
                player = exoPlayer
                this.useController = useController  // 是否显示时间轴/暂停按钮
                setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
            }
        }
    )

    // 3) 生命周期与释放
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}
