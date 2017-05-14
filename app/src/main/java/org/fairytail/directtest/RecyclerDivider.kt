package org.fairytail.directtest

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

/**
 * Created by Alex on 5/9/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */

object RecyclerDivider {
    val horizontal: HorizontalDividerItemDecoration by lazy {
        HorizontalDividerItemDecoration.Builder(App.ctx)
                .drawable(R.drawable.divider_horizontal_gradient_left)
                .sizeResId(R.dimen.material_divider_height)
                .build()
    }
}