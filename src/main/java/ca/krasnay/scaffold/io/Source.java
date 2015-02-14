/* Copyright 2015 John Krasnay
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.krasnay.scaffold.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Internal interface representing a source of an input stream.
 *
 * @author <a href="mailto:john@krasnay.ca">John Krasnay</a>
 */
public interface Source {

    /**
     * Returns a new input stream on the source.
     */
    public InputStream getInputStream() throws IOException;

    /**
     * Closes the given input stream.
     *
     * @param in
     *            Stream returned by {@link #getInputStream()}. May be null.
     */
    public void close(InputStream in);
}