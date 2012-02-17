/*
 * This file is part of SpoutAPI (http://www.spout.org/).
 *
 * SpoutAPI is licensed under the SpoutDev License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.api.protocol;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;

public abstract class MessageCodec<T extends Message> {
	private final Class<T> clazz;
	private final int opcode;
	private final boolean expanded;

	public MessageCodec(Class<T> clazz, int opcode) {
		this(clazz, opcode, false);
	}

	public MessageCodec(Class<T> clazz, int opcode, boolean expanded) {
		this.clazz = clazz;
		this.opcode = opcode;
		this.expanded = expanded;
	}

	public final Class<T> getType() {
		return clazz;
	}

	public final int getOpcode() {
		return opcode;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public abstract ChannelBuffer encode(T message) throws IOException;

	public abstract T decode(ChannelBuffer buffer) throws IOException;
}
