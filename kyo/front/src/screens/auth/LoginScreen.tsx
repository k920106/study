import React, { useRef } from 'react';
import { StyleSheet, View, SafeAreaView, TextInput } from 'react-native';
import InputField from '@/components/common/InputField';
import CustomButton from '@/components/common/CustomButton';
import useForm from '@/hooks/useForm';
import useAuth from '@/hooks/queries/useAuth';
import { validateLogin } from '@/utils';
import Toast from 'react-native-toast-message';
import { errorMessages } from '@/constants';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

function LoginScreen() {
    const { loginMutation } = useAuth();
    const passwordRef = useRef<TextInput | null>(null);
    const { theme } = useThemeStore();
    const styles = styling(theme);

    const login = useForm({
        initialValue: { email: '', password: '' },
        validate: validateLogin,
    });

    const handleSubmit = () => {
        loginMutation.mutate(login.values, {
            onError: error =>
                Toast.show({
                    type: 'error',
                    text1: error.response?.data.message || errorMessages.UNEXPECT_ERROR,
                    position: 'bottom',
                    visibilityTime: 2000,
                }),
        });
    };

    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.inputContainer}>
                <InputField placeholder="이메일"
                    autoFocus
                    error={login.errors.email}
                    touched={login.touched.email}
                    inputMode="email"
                    returnKeyType="next"
                    blurOnSubmit={false}
                    onSubmitEditing={() => passwordRef.current?.focus()}
                    {...login.getTextInputProps('email')} />
                <InputField placeholder="비밀번호"
                    error={login.errors.password}
                    touched={login.touched.password}
                    secureTextEntry
                    returnKeyType="join"
                    ref={passwordRef}
                    onSubmitEditing={handleSubmit}
                    {...login.getTextInputProps('password')} />
            </View>
            <CustomButton label="로그인"
                variant="filled"
                size="large"
                onPress={handleSubmit} />
        </SafeAreaView>
    );
}

//const styles = StyleSheet.create({
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
        container: {
            flex: 1,
            margin: 30,
        },
        inputContainer: {
            gap: 20,
        },
    });

export default LoginScreen;