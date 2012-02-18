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
import org.spout.vanilla.protocol.msg.SpawnVehicleMessage;

public final class SpawnVehicleCodec extends MessageCodec<SpawnVehicleMessage> {
	public SpawnVehicleCodec() {
		super(SpawnVehicleMessage.class, 0x17);
	}

	@Override
	public SpawnVehicleMessage decode(ChannelBuffer buffer) throws IOException {
		int id = buffer.readInt();
		int type = buffer.readUnsignedByte();
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		int fireballId = buffer.readInt();
		if (fireballId != 0) {
			int fireballX = buffer.readShort();
			int fireballY = buffer.readShort();
			int fireballZ = buffer.readShort();
			return new SpawnVehicleMessage(id, type, x, y, z, fireballId, fireballX, fireballY, fireballZ);
		}
		return new SpawnVehicleMessage(id, type, x, y, z);
	}

	@Override
	public ChannelBuffer encode(SpawnVehicleMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.buffer(message.hasFireball() ? 27 : 21);
		buffer.writeInt(message.getId());
		buffer.writeByte(message.getType());
		buffer.writeInt(message.getX());
		buffer.writeInt(message.getY());
		buffer.writeInt(message.getZ());
		buffer.writeInt(message.getFireballId());
		if (message.hasFireball()) {
			buffer.writeShort(message.getFireballX());
			buffer.writeShort(message.getFireballY());
			buffer.writeShort(message.getFireballZ());
		}
		return buffer;
	}
}
