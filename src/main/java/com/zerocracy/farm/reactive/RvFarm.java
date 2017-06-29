/**
 * Copyright (c) 2016-2017 Zerocracy
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
package com.zerocracy.farm.reactive;

import com.zerocracy.jstk.Farm;
import com.zerocracy.jstk.Project;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import lombok.EqualsAndHashCode;
import org.cactoos.func.SyncFunc;
import org.cactoos.list.MappedIterable;

/**
 * Reactive farm.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode(of = "origin")
public final class RvFarm implements Farm {

    /**
     * Original farm.
     */
    private final Farm origin;

    /**
     * Brigade.
     */
    private final Brigade brigade;

    /**
     * Pool of projects.
     */
    private final Map<Project, Project> pool;

    /**
     * Service.
     */
    private final ExecutorService service;

    /**
     * Ctor.
     * @param farm Original farm
     * @param bgd Stakeholders
     * @param svc Service
     */
    public RvFarm(final Farm farm, final Brigade bgd,
        final ExecutorService svc) {
        this.origin = farm;
        this.brigade = bgd;
        this.pool = new HashMap<>(0);
        this.service = svc;
    }

    @Override
    public Iterable<Project> find(final String query) throws IOException {
        return new MappedIterable<>(
            this.origin.find(query),
            new SyncFunc<>(
                p -> {
                    if (!this.pool.containsKey(p)) {
                        this.pool.put(
                            p, new RvProject(p, this.brigade, this.service)
                        );
                    }
                    return this.pool.get(p);
                }
            )
        );
    }

}