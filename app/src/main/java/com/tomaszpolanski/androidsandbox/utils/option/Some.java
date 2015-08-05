package com.tomaszpolanski.androidsandbox.utils.option;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.internal.util.Predicate;
import com.tomaszpolanski.androidsandbox.models.Errors.NullError;
import com.tomaszpolanski.androidsandbox.models.Errors.ResultError;
import com.tomaszpolanski.androidsandbox.utils.Linq;
import com.tomaszpolanski.androidsandbox.utils.result.Result;

import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;

public final class Some<T> extends Option<T> {

    @NonNull
    private final T mValue;

    Some(@NonNull final T value) {
        mValue = value;
    }

    @Override
    public boolean getIsSome() {
        return true;
    }

    @Override
    public void iter(@NonNull final Action1<T> action) {
        action.call(mValue);
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> map(@NonNull final Func1<T, OUT> f) {
        return Option.ofObj(f.call(mValue));
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> flatMap(@NonNull final Func1<T, Option<OUT>> f) {
        return f.call(mValue);
    }

    @NonNull
    @Override
    public Option<T> filter(@NonNull final Func1<T, Boolean> predicate) {
        return predicate.call(mValue) ? this : NONE;
    }

    @NonNull
    @Override
    public Option<T> orOption(@NonNull final Func0<Option<T>> f) {
        return this;
    }

    @NonNull
    @Override
    public T orDefault(@NonNull final Func0<T> def) {
        return mValue;
    }

    @NonNull
    @Override
    public T getUnsafe() {
        return mValue;
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> ofType(@NonNull Class<OUT> type) {
        return type.isInstance(mValue) ? Option.ofObj(type.cast(mValue)) : Option.NONE;
    }

    @Nullable
    @Override
    public <OUT> OUT match(@NonNull Func1<T, OUT> fSome,
                           @NonNull Func0<OUT> fNone) {
        return fSome.call(mValue);
    }

    @NonNull
    @Override
    public <IN, OUT2> Option<OUT2> lift(@NonNull final Option<IN> option,
                                        @NonNull final Func2<T, IN, OUT2> f) {
        return option.map(b -> f.call(mValue, b));
    }

    @NonNull
    @Override
    public <IN1, IN2, OUT> Option<OUT> lift(@NonNull Option<IN1> option1,
                                            @NonNull Option<IN2> option2,
                                            @NonNull Func3<T, IN1, IN2, OUT> f) {
        return option1.lift(option2, (o1, o2) -> f.call(mValue, o1, o2));
    }

    @NonNull
    @Override
    public <IN1, IN2, IN3, OUT> Option<OUT> lift(@NonNull Option<IN1> option1,
                                                 @NonNull Option<IN2> option2,
                                                 @NonNull Option<IN3> option3,
                                                 @NonNull Func4<T, IN1, IN2, IN3, OUT> f) {
        return option1.lift(option2, option3, (o1, o2, o3) -> f.call(mValue, o1, o2, o3));
    }

    @NonNull
    @Override
    public Linq<T> toLinq() {
        return Linq.just(mValue);
    }

    @Override
    public int hashCode() {
        return mValue.hashCode();
    }

    @NonNull
    @Override
    public Result<T> toResult(@NonNull final ResultError message) {
        return Result.ofObj(mValue, new NullError("mValue"));
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(final Object o) {
        return Option.ofObj(o)
                     .ofType(Some.class)
                     .filter(some -> some.getUnsafe().equals(mValue)) != Option.NONE;
    }

    @Override
    public String toString() {
        return mValue.toString();
    }
}