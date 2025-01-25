import { Module } from '@nestjs/common';
import { ImageController } from './image.controller';
import { AuthModule } from 'src/auth/auth.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Image } from 'src/image/image.entity';

@Module({
    imports: [TypeOrmModule.forFeature([Image]), AuthModule],
    controllers: [ImageController]
})
export class ImageModule { }
