import React, { ReactNode } from 'react';
import { StyleSheet, Text, Pressable, PressableProps, View } from 'react-native';

import { colors } from '@/constants';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

interface SettingItemProps extends PressableProps {
    title: string;
    subTitle?: string;
    icon?: ReactNode;
    color?: string;
}

function SettingItem({
    title,
    subTitle,
    icon = null,
    color,
    ...props
}: SettingItemProps) {
    const { theme } = useThemeStore();
    const styles = styling(theme);
    
    return (
        <Pressable
            style={({ pressed }) => [
                styles.container,
                pressed && styles.pressedContainer,
            ]}
            {...props}>
            {icon}
            <View style={styles.titleContainer}>
                {/* <Text style={[styles.titleText, { color: color ?? colors.BLACK }]}> */}
                <Text style={[styles.titleText, { color: color ?? colors[theme].BLACK }]}>
                    {title}
                </Text>
                {subTitle && <Text style={styles.subTitleText}>{subTitle}</Text>}
            </View>
        </Pressable>
    );
}

//const styles = StyleSheet.create({
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
        container: {
            flexDirection: 'row',
            alignItems: 'center',
            gap: 10,
            padding: 15,
            //backgroundColor: colors.WHITE,
            backgroundColor: colors[theme].WHITE,
            //borderColor: colors.GRAY_200,
            borderColor: colors[theme].GRAY_200,
            borderBottomWidth: StyleSheet.hairlineWidth,
            borderTopWidth: StyleSheet.hairlineWidth,
        },
        pressedContainer: {
            //backgroundColor: colors.GRAY_200,
            backgroundColor: colors[theme].GRAY_200,
        },
        titleContainer: {
            flex: 1,
            flexDirection: 'row',
            justifyContent: 'space-between',
        },
        titleText: {
            fontSize: 16,
            fontWeight: '500',
            //color: colors.BLACK,
            color: colors[theme].BLACK,
        },
        subTitleText: {
            //color: colors.GRAY_500,
            color: colors[theme].GRAY_500,
        },
    });

export default SettingItem;