/*
 * Copyright (c) 2016-2018 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy.kpi;

/**
 * KPI metric statistics.
 *
 * @since 1.0
 */
public interface KpiStats {

    /**
     * Empty statistics.
     */
    KpiStats EMPTY = new KpiStats() {
        @Override
        public double avg() {
            return 0.0;
        }
        @Override
        public double min() {
            return 0.0;
        }
        @Override
        public double max() {
            return 0.0;
        }
        @Override
        public double count() {
            return 0.0;
        }
    };

    /**
     * Average for period.
     *
     * @return Average value
     */
    double avg();

    /**
     * Minimum for period.
     *
     * @return Minimum value
     */
    double min();

    /**
     * Maximum for period.
     *
     * @return Maximum value
     */
    double max();

    /**
     * Metrics count for period.
     *
     * @return Count value
     */
    double count();
}
