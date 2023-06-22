/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.api.windowing.triggers;

/**
 * Result type for trigger methods. This determines what happens with the window, for example
 * whether the window function should be called, or the window should be discarded.
 *
 * <p>If a {@link Trigger} returns {@link #FIRE} or {@link #FIRE_AND_PURGE} but the window does not
 * contain any data the window function will not be invoked, i.e. no data will be produced for the
 * window.
 * <p>
 * 触发器方法的结果类型。 这决定了窗口会发生什么，例如是否应该调用窗口函数，或者应该丢弃窗口。
 * 如果触发器返回 FIRE 或 FIRE_AND_PURGE 但窗口不包含任何数据，则不会调用窗口函数，即不会为窗口生成任何数据。
 * </p>
 */
public enum TriggerResult {

    /** No action is taken on the window. */
    CONTINUE(false, false),

    /**
     * {@code FIRE_AND_PURGE} evaluates the window function and emits the window result.
     * <p>
     * FIRE_AND_PURGE 评估窗口函数并发出窗口结果。
     */
    FIRE_AND_PURGE(true, true),

    /**
     * On {@code FIRE}, the window is evaluated and results are emitted. The window is not purged,
     * though, all elements are retained.
     * <p>
     * 处于FIRE状态，窗口被评估并发送结果。 窗口没有被清空，所有元素都被保留了下来。
     */
    FIRE(true, false),

    /**
     * All elements in the window are cleared and the window is discarded, without evaluating the
     * window function or emitting any elements.
     * <p>
     * 窗口中的所有元素都被清除并且窗口被丢弃，而不评估窗口函数或发出任何元素。
     */
    PURGE(false, true);

    // ------------------------------------------------------------------------

    private final boolean fire;
    private final boolean purge;

    TriggerResult(boolean fire, boolean purge) {
        this.purge = purge;
        this.fire = fire;
    }

    public boolean isFire() {
        return fire;
    }

    public boolean isPurge() {
        return purge;
    }
}
