const ArrowLeftSvg = ({
                          diameter = 15,
                          onClick = () => {},
                          color = "var(--icon1)",
                      }) => {
    return (
        <svg
            onClick={onClick}
            width={diameter * 0.53}
            height={diameter}
            viewBox={`0 0 8 15`}
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
        >
            <path
                id="Vector 278"
                d="M7 1.5L1 7.5L7 13.5"
                stroke={color}
                strokeWidth="2"
                strokeLinecap="round"
                strokeLinejoin="round"
            />
        </svg>
    );
};

export default ArrowLeftSvg;
