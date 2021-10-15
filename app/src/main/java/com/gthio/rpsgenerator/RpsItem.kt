package com.gthio.rpsgenerator

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class RpsItem(@DrawableRes val icon: Int, @StringRes val contentDescription: Int) {
    Rock(R.drawable.ic_baseline_circle_24, R.string.rock_desc),
    Paper(R.drawable.ic_baseline_pan_tool_24, R.string.paper_desc),
    Scissors(R.drawable.ic_baseline_content_cut_24, R.string.scissor_desc),
    Random(R.drawable.ic_baseline_shuffle_24, R.string.random_desc)
}