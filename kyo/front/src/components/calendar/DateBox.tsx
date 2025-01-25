import { colors } from '@/constants';
import useThemeStore from '@/store/useThemeStore';
import { ThemeMode } from '@/types';
import React from 'react';
import { Dimensions, Pressable, Text } from 'react-native';
import { StyleSheet, View } from 'react-native';

interface DateBoxProps {
    date: number;
    isToday: boolean;
    selectedDate: number;
    hasSchedule: boolean;
    onPressDate: (date: number) => void;
}

const deviceWidth = Dimensions.get('window').width;

function DateBox({
    date,
    selectedDate,
    hasSchedule,
    onPressDate,
    isToday
}: DateBoxProps) {
    const { theme } = useThemeStore();
    const styles = styling(theme);
    
    return (
        <Pressable style={styles.container} onPress={() => onPressDate(date)}>
            {date > 0 && (
                <>
                    <View
                        style={[
                            styles.dateContainer,
                            selectedDate === date && styles.selectedContainer,
                            selectedDate === date && isToday && styles.selectedTodayContainer,
                        ]}>
                        <Text
                            style={[
                                styles.dateText,
                                isToday && styles.todayText,
                                selectedDate === date && styles.selectedDateText,
                                selectedDate === date && isToday && styles.selectedTodayText,
                            ]}>
                            {date}
                        </Text>
                    </View>
                    {hasSchedule && <View style={styles.scheduleIndicator} />}
                </>
            )}
        </Pressable>
    );
}

//const styles = StyleSheet.create({
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
        container: {
            width: deviceWidth / 7,
            height: deviceWidth / 7,
            borderTopWidth: StyleSheet.hairlineWidth,
            //borderTopColor: colors.GRAY_200,
            borderTopColor: colors[theme].GRAY_200,
            alignItems: 'center',
        },
        dateContainer: {
            marginTop: 5,
            alignItems: 'center',
            justifyContent: 'center',
            width: 28,
            height: 28,
            borderRadius: 28,
        },
        selectedContainer: {
            //backgroundColor: colors.BLACK,
            backgroundColor: colors[theme].BLACK,
        },
        selectedTodayContainer: {
            //backgroundColor: colors.PINK_700,
            backgroundColor: colors[theme].PINK_700,
        },
        dateText: {
            fontSize: 17,
            //color: colors.BLACK,
            color: colors[theme].BLACK,
        },
        todayText: {
            //color: colors.PINK_700,
            color: colors[theme].PINK_700,
            fontWeight: 'bold',
        },
        selectedDateText: {
            //color: colors.WHITE,
            color: colors[theme].WHITE,
            fontWeight: 'bold',
        },
        selectedTodayText: {
            //color: colors.WHITE,
            color: colors[theme].WHITE,
        },
        scheduleIndicator: {
            marginTop: 2,
            width: 6,
            height: 6,
            borderRadius: 6,
            //backgroundColor: colors.GRAY_500,
            backgroundColor: colors[theme].GRAY_500,
        },
    });

export default DateBox;