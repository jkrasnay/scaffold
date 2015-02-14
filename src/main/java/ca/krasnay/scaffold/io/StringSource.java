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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringSource implements Source {

    private String charsetName;

    private String s;

    public StringSource(String s) {
        this(s, IOUtils.UTF_8);
    }

    public StringSource(String s, String charsetName) {
        this.s = s;
        this.charsetName = charsetName;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(s.getBytes(charsetName));
    }

    @Override
    public void close(InputStream in) {
    }

}
