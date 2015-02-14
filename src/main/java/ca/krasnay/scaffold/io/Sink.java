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
import java.io.OutputStream;

/**
 * Represents a place to which bytes can be sent.
 *
 * @author <a href="mailto:john@krasnay.ca">John Krasnay</a>
 */
public interface Sink {

    /**
     * Returns a new output stream on the sink.
     */
    public OutputStream getOutputStream() throws IOException;

    /**
     * Closes the given stream.
     *
     * @param out Stream returned by {@link #getOutputStream()}. May be null.
     */
    public void close(OutputStream out);
}