/*
 * This file is part of Vanilla (http://www.spout.org/).
 *
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev license version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package codec;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import org.spout.api.protocol.MessageCodec;
import org.spout.vanilla.protocol.msg.MultiBlockChangeMessage;

public final class MultiBlockChangeCodec extends MessageCodec<MultiBlockChangeMessage> {
	public MultiBlockChangeCodec() {
		super(MultiBlockChangeMessage.class, 0x34);
	}

	@Override
	public MultiBlockChangeMessage decode(ChannelBuffer buffer) throws IOException {
		int chunkX = buffer.readInt();
		int chunkZ = buffer.readInt();
		int changes = buffer.readUnsignedShort();

		short[] coordinates = new short[changes];
		byte[] types = new byte[changes];
		byte[] metadata = new byte[changes];

		for (int i = 0; i < changes; i++) {
			coordinates[i] = buffer.readShort();
		}
		buffer.readBytes(types);
		buffer.readBytes(metadata);

		return new MultiBlockChangeMessage(chunkX, chunkZ, coordinates, types, metadata);
	}

	@Override
	public ChannelBuffer encode(MultiBlockChangeMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(message.getChunkX());
		buffer.writeInt(message.getChunkZ());
		buffer.writeShort(message.getChanges());

		short[] coordinates = message.getCoordinates();
		for (short coordinate : coordinates) {
			buffer.writeShort(coordinate);
		}

		buffer.writeBytes(message.getTypes());
		buffer.writeBytes(message.getMetadata());
		return buffer;
	}
}
