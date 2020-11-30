/*
 * Copyright 2020 Baptiste Candellier
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package fr.outadoc.homeslide.wear.service

import android.content.Context
import android.widget.RemoteViews
import com.google.android.clockwork.tiles.TileProviderService

class HomeTileProviderService: TileProviderService() {

    private var mContext: Context? = null
    private var id = -1

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }

    override fun onDestroy() {
        Log.d(TAG, "destroying service...")
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        val result = super.onUnbind(intent)
        Log.d(TAG, "Service unbound")
        return result
    }

    override fun onTileUpdate(tileId: Int) {
        Log.d(TAG, "onTileUpdate called with: tileId = $tileId")
        if (!isIdForDummyData(tileId)) {
            id = tileId
            sendRemoteViews()
        }
    }

    override fun onTileFocus(tileId: Int) {
        super.onTileFocus(tileId)
        Log.d(TAG, "onTileFocus called with: tileId = $tileId")
        if (!isIdForDummyData(tileId)) {
            id = tileId
        }
    }

    override fun onTileBlur(tileId: Int) {
        super.onTileBlur(tileId)
        Log.d(TAG, "onTileBlur called with: tileId = $tileId")
        if (!isIdForDummyData(tileId)) {
            id = tileId
        }
    }

    private fun sendRemoteViews() {
        Log.d(TAG, "sendRemoteViews")

        val remoteViews = RemoteViews(this.packageName, R.layout.tile)
        // *** Update your tile UI here

        val bob = TileData.Builder()
            .setRemoteViews(remoteViews)
        sendData(id, bob.build())
    }

}