/*
 * Copyright 2020 Niklas van Schrick and the contributors of this project
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

package de.taucher.atlassian_statuspage_api;

public class Methods {
	
	public static boolean isEmpty(CharSequence sequence) {
        return sequence == null || sequence.length() == 0;
    }

	public static int countChar(CharSequence sequence, char c) {
		if (isEmpty(sequence))
            return 0;
        int count = 0;
        for(int i = 0; i<sequence.length(); i++) {
            if(sequence.charAt(i) == c) {
                count++;
            }
        }
        return count;
	}
}
