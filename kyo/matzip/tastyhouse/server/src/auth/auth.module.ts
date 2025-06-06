import { Module } from '@nestjs/common';
import { AuthController } from './auth.controller';
import { AuthService } from './auth.service';
import { User } from './user.entity';
import { TypeOrmModule } from '@nestjs/typeorm';
import { JwtModule } from '@nestjs/jwt';
import { PassportModule } from '@nestjs/passport';
import { JwtStrategy } from './jwt.strategy';

@Module({
    imports: [
        PassportModule.register({ defaultStrategy: 'jwt' }),
        JwtModule.register({}),
        TypeOrmModule.forFeature([User])
    ],
    controllers: [AuthController],
    providers: [AuthService, JwtStrategy],
    exports: [JwtStrategy, PassportModule],
})
export class AuthModule { }
