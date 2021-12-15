package com.devbatch.kaswaa.databinding;
import com.devbatch.kaswaa.R;
import com.devbatch.kaswaa.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemSearchSuggestionProductsBindingImpl extends ItemSearchSuggestionProductsBinding implements com.devbatch.kaswaa.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.appcompat.widget.LinearLayoutCompat mboundView0;
    @NonNull
    private final androidx.appcompat.widget.AppCompatImageView mboundView1;
    @NonNull
    private final androidx.appcompat.widget.AppCompatTextView mboundView2;
    @NonNull
    private final androidx.appcompat.widget.AppCompatTextView mboundView3;
    @NonNull
    private final androidx.appcompat.widget.AppCompatTextView mboundView4;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback291;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemSearchSuggestionProductsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private ItemSearchSuggestionProductsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            );
        this.mboundView0 = (androidx.appcompat.widget.LinearLayoutCompat) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (androidx.appcompat.widget.AppCompatImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (androidx.appcompat.widget.AppCompatTextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (androidx.appcompat.widget.AppCompatTextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (androidx.appcompat.widget.AppCompatTextView) bindings[4];
        this.mboundView4.setTag(null);
        setRootTag(root);
        // listeners
        mCallback291 = new com.devbatch.kaswaa.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.handler == variableId) {
            setHandler((com.devbatch.kaswaa.handlers.SearchSuggestionProductHandler) variable);
        }
        else if (BR.data == variableId) {
            setData((com.devbatch.kaswaa.models.extra.SuggestionProductData) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setHandler(@Nullable com.devbatch.kaswaa.handlers.SearchSuggestionProductHandler Handler) {
        this.mHandler = Handler;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }
    public void setData(@Nullable com.devbatch.kaswaa.models.extra.SuggestionProductData Data) {
        this.mData = Data;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.data);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.devbatch.kaswaa.handlers.SearchSuggestionProductHandler handler = mHandler;
        int dataHasSpecialPriceViewVISIBLEViewGONE = 0;
        int dataHasSpecialPriceMboundView4AndroidColorGrey400MboundView4AndroidColorProductPriceColor = 0;
        java.lang.String dataDominantColor = null;
        android.graphics.drawable.Drawable dataHasSpecialPriceMboundView4AndroidDrawableBgStrikethroughJavaLangObjectNull = null;
        java.lang.String dataProductName = null;
        boolean dataHasSpecialPrice = false;
        java.lang.String dataThumbNail = null;
        com.devbatch.kaswaa.models.extra.SuggestionProductData data = mData;
        java.lang.String dataPrice = null;
        java.lang.String dataSpecialPrice = null;

        if ((dirtyFlags & 0x6L) != 0) {



                if (data != null) {
                    // read data.dominantColor
                    dataDominantColor = data.getDominantColor();
                    // read data.productName
                    dataProductName = data.getProductName();
                    // read data.hasSpecialPrice
                    dataHasSpecialPrice = data.getHasSpecialPrice();
                    // read data.thumbNail
                    dataThumbNail = data.getThumbNail();
                    // read da