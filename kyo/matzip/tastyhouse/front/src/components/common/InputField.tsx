import React, { forwardRef, ReactNode, ForwardedRef, useRef } from 'react';
import { Dimensions, StyleSheet, TextInput, View, TextInputProps, Text, Pressable } from 'react-native';
import { mergeRefs } from '@/utils/common';
import { colors } from '@/constants';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

interface InputFieldProps extends TextInputProps {
    disabled?: boolean;
    error?: string;
    touched?: boolean;
    icon?: ReactNode;
}

const deviceHeight = Dimensions.get('screen').height;

const InputField = forwardRef(
    (
        { disabled = false, error, touched, icon = null, ...props }: InputFieldProps,
        ref?: ForwardedRef<TextInput>,
    ) => {
        const innerRef = useRef<TextInput | null>(null);
        const { theme } = useThemeStore();
        const styles = styling(theme);

        const handlePressInput = () => {
            innerRef.current?.focus();
        };

        return (
            <Pressable onPress={handlePressInput}>
                <View
                    style={[
                        styles.container,
                        disabled && styles.disabled,
                        props.multiline && styles.multiLine,
                        touched && Boolean(error) && styles.inputError
                    ]}>
                    <View style={Boolean(icon) && styles.innerContainer}>
                        {icon}
                        <TextInput
                            ref={ref ? mergeRefs(innerRef, ref) : innerRef}
                            editable={!disabled}
                            //placeholderTextColor={colors.GRAY_500}
                            placeholderTextColor={colors[theme].GRAY_500}
                            style={[styles.input, disabled && styles.disabled]}
                            autoCapitalize="none"
                            spellCheck={false}
                            autoCorrect={false}
                            {...props}
                        />
                    </View>
                    {touched && Boolean(error) && (
                        <Text style={styles.error}>{error}</Text>
                    )}
                </View>
            </Pressable>
        );
    },
);

//const styles = StyleSheet.create({
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
        container: {
            borderWidth: 1,
            //borderColor: colors.GRAY_200,
            borderColor: colors[theme].GRAY_200,
            padding: deviceHeight > 700 ? 15 : 10,
        },
        multiLine: {
            paddingBottom: deviceHeight > 700 ? 45 : 30,
        },
        input: {
            fontSize: 16,
            //color: colors.BLACK,
            color: colors[theme].BLACK,
            padding: 0,
        },
        innerContainer: {
            flexDirection: 'row',
            alignItems: 'center',
            gap: 5,
        },
        disabled: {
            //backgroundColor: colors.GRAY_200,
            backgroundColor: colors[theme].GRAY_200,
            //color: colors.GRAY_700,
            color: colors[theme].GRAY_700,
        },
        inputError: {
            borderWidth: 1,
            //borderColor: colors.RED_300,
            borderColor: colors[theme].RED_300,
        },
        error: {
            //color: colors.RED_500,
            color: colors[theme].RED_500,
            fontSize: 12,
            paddingTop: 5,
        },
    });

export default InputField;