package com.example.mediaplayer;

import android.media.MediaPlayer;

public interface IPlayer {
    // progress 是当前播放进度（以毫秒为单位）
    // duration 是音频的总时长（以毫秒为单位）
    void OnProgressListener(MediaPlayer mp, int progress, int duration);


}
